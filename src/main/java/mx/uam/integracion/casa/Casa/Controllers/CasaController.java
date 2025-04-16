package mx.uam.integracion.casa.Casa.Controllers;

import mx.uam.integracion.casa.Casa.dto.CasaDTO;
import mx.uam.integracion.casa.Casa.service.CasaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CasaController {
    private final CasaService casaService;

    public CasaController(CasaService casaService) {
        this.casaService = casaService;
    }

    @PostMapping("/casa")
    public ResponseEntity<CasaDTO> createCasa(@RequestBody CasaDTO casaDTO) {
        CasaDTO createdCasa = casaService.createCasa(casaDTO);
        return ResponseEntity.ok(createdCasa);
    }

    @GetMapping("/casa")
    public ResponseEntity<List<CasaDTO>> getCasas() {
        List<CasaDTO> casas = List.of(casaService.getAllCasas());
        return ResponseEntity.ok(casas);
    }

    @PutMapping("/casa/{id}")
    public ResponseEntity<CasaDTO> updateCasa(@PathVariable Long id, @RequestBody CasaDTO casaDTO) {
        CasaDTO updatedCasa = casaService.updateCasa(id, casaDTO);
        return ResponseEntity.ok(updatedCasa);
    }

    @DeleteMapping("/casa/{id}")
    public ResponseEntity<Void> deleteCasa(@PathVariable Long id) {
        casaService.deleteCasa(id);
        return ResponseEntity.noContent().build();
    }
}