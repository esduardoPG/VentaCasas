package mx.uam.integracion.casa.Casa.service.impl;

import mx.uam.integracion.casa.Casa.dto.CasaDTO;
import mx.uam.integracion.casa.Casa.entity.Casa;
import mx.uam.integracion.casa.Casa.repository.CasaRepository;
import mx.uam.integracion.casa.Casa.service.CasaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CasaServiceimpl implements CasaService {
    private final CasaRepository casaRepository;

    public CasaServiceimpl(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    @Override
    public CasaDTO createCasa(CasaDTO casaDTO) {
        if (casaRepository.existsById(casaDTO.getIdCasa())) {
            throw new RuntimeException("Ya existe una casa con el ID: " + casaDTO.getIdCasa());
        }

        Casa casa = new Casa();
        casa.setIdCasa(casaDTO.getIdCasa());
        casa.setColor(casaDTO.getColor());
        casa.setPrecio(casaDTO.getPrecio());
        casa.setTamaño(casaDTO.getTamaño());

        Casa savedCasa = casaRepository.save(casa);

        return convertToDTO(savedCasa);
    }

    @Override
    public CasaDTO[] getAllCasas() {
        List<Casa> casas = casaRepository.findAll();
        return casas.stream()
                .map(this::convertToDTO)
                .toArray(CasaDTO[]::new);
    }

    @Override
    public CasaDTO updateCasa(Long id, CasaDTO casaDTO) {
        Optional<Casa> optionalCasa = casaRepository.findById(id);
        if (optionalCasa.isPresent()) {
            Casa casa = optionalCasa.get();
            casa.setColor(casaDTO.getColor());
            casa.setPrecio(casaDTO.getPrecio());
            casa.setTamaño(casaDTO.getTamaño());

            Casa updatedCasa = casaRepository.save(casa);
            return convertToDTO(updatedCasa);
        } else {
            throw new RuntimeException("No se encontró una casa con el ID: " + id);
        }
    }

    @Override
    public void deleteCasa(Long id) {
        if (!casaRepository.existsById(id)) {
            throw new RuntimeException("No se encontró una casa con el ID: " + id);
        }
        casaRepository.deleteById(id);
    }

    private CasaDTO convertToDTO(Casa casa) {
        CasaDTO casaDTO = new CasaDTO();
        casaDTO.setIdCasa(casa.getIdCasa());
        casaDTO.setColor(casa.getColor());
        casaDTO.setPrecio(casa.getPrecio());
        casaDTO.setTamaño(casa.getTamaño());
        return casaDTO;
    }
}