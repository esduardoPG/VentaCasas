package mx.uam.integracion.casa.Casa.service;

import mx.uam.integracion.casa.Casa.dto.CasaDTO;

public interface CasaService {
    CasaDTO createCasa(CasaDTO casaDTO);
    CasaDTO[] getAllCasas();
    CasaDTO updateCasa(Long id, CasaDTO casaDTO);
    void deleteCasa(Long id);
}