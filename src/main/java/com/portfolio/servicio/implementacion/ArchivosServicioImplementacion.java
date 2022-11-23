package com.portfolio.servicio.implementacion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;

@Service
public class ArchivosServicioImplementacion {

    @Value("${aplicacion.directorio}")
    private String directorio;

    public void crearDirectorio() {
        Path ruta = Paths.get(directorio);
        try {
            Files.createDirectory(ruta);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio");
        }
    }

    public URI guardarArchivo(MultipartFile archivo) {
        if (archivo.getOriginalFilename() != null) {
            Path ruta = Paths.get(directorio);
            Path rutaArchivo = ruta.resolve(Instant.now().toEpochMilli() + "_" + archivo.getOriginalFilename());
            try {
                File archivoCreado = new File(rutaArchivo.toString());
                OutputStream salida = new FileOutputStream(archivoCreado);
                salida.write(archivo.getBytes());
                salida.close();
            } catch (IOException ex) {
                throw new RuntimeException("Archivo no pudo ser creado");
            }
            return ruta.toUri().relativize(rutaArchivo.toUri());
        }
        throw new RuntimeException("Fallo al leerse el archivo");
    }

    public byte[] descargarArchivo(String nombreArchivo) {
        Path ruta = Paths.get(directorio);
        Path rutaArchivo = ruta.resolve(nombreArchivo);
        try {
            InputStream archivo = new FileInputStream(rutaArchivo.toString());
            byte[] arrayBytes = archivo.readAllBytes();
            archivo.close();
            return arrayBytes;
        } catch (IOException ex) {
            ex.printStackTrace();
            borrarArchivo(nombreArchivo);
        }
        return null;
    }

    public void borrarArchivo(String nombreArchivo) {
        try {
            Path ruta = Paths.get(directorio);
            Path rutaArchivo = ruta.resolve(nombreArchivo);
            Files.delete(rutaArchivo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
