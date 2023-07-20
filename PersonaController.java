package pe.edu.cibertec.DAW1_CL2_GustavoCalderon;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    private PersonaRepository personaRepository;

    PersonaController(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @GetMapping
    public String list(Model model) {
        List<Persona> personas = personaRepository.findAll();
        model.addAttribute("personas", personas);
        return "personas/listar";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("persona", new Persona());
        return "personas/create";
    }

    @PostMapping
    public String store(Persona persona) {
        personaRepository.save(persona);
        return "redirect:/personas";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isEmpty()) {
            return "404";
        }

        model.addAttribute("persona", personaOptional.get());
        return "personas/detail";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isEmpty()) {
            return "404";
        }

        model.addAttribute("persona", personaOptional.get());
        return "personas/edit";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable Long id, Persona dataFormulario) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isEmpty()) {
            return "404";
        }

        Persona persona = personaOptional.get();
        persona.setNombre(dataFormulario.getNombre());
        persona.setApellido(dataFormulario.getApellido());
        persona.setDni(dataFormulario.getDni());
        persona.setEdad(dataFormulario.getEdad());
        personaRepository.save(persona); // UPDATE

        return "redirect:/personas";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        personaRepository.deleteById(id);
        return "redirect:/personas";
    }

}
