package com.example.demo;

@GetMapping("/{id}")
public ResponseEntity<Anexo> getAnexoById(@PathVariable(value = "id") Long anexoId) {
    Optional<Anexo> anexo = anexoRepository.findById(anexoId);
    if (anexo.isPresent()) {
        return ResponseEntity.ok().body(anexo.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Anexo> updateAnexo(@PathVariable(value = "id") Long anexoId,
                                          @RequestBody Anexo anexoDetails) {
    Optional<Anexo> anexo = anexoRepository.findById(anexoId);
    if (anexo.isPresent()) {
        Anexo updatedAnexo = anexo.get();
        updatedAnexo.setNombre(anexoDetails.getNombre());
        updatedAnexo.setTipo(anexoDetails.getTipo());
        updatedAnexo.setUrl(anexoDetails.getUrl());
        Anexo savedAnexo = anexoRepository.save(updatedAnexo);
        return ResponseEntity.ok().body(savedAnexo);
    } else {
        return ResponseEntity.notFound().build();
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteAnexo(@PathVariable(value = "id") Long anexoId) {
    Optional<Anexo> anexo = anexoRepository.findById(anexoId);
    if (anexo.isPresent()) {
        anexoRepository.delete(anexo.get());
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}

