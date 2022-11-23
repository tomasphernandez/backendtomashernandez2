package com.portfolio;

import com.portfolio.entidad.Persona;
import com.portfolio.entidad.SobreMi;
import com.portfolio.entidad.Usuario;
import com.portfolio.repositorio.PersonaRepositorio;
import com.portfolio.repositorio.SobreMiRepositorio;
import com.portfolio.repositorio.UsuarioRepositorio;
import com.portfolio.servicio.implementacion.ArchivosServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class PortfolioApplication implements CommandLineRunner {

	@Value("${aplicacion.nombre_usuario}")
	private String nombreUsuario;

	@Value("${aplicacion.password}")
	private String password;

	@Value("${aplicacion.directorio}")
	private String directorio;

	@Autowired
	private PersonaRepositorio personaRepositorio;

	@Autowired
	private SobreMiRepositorio sobreMiRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ArchivosServicioImplementacion archivosServicioImplementacion;

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (personaRepositorio.count() == 0) {
			Persona persona = Persona.builder()
					.nombre("Tomas")
					.apellido("Hernandez")
					.build();

			personaRepositorio.save(persona);

		}
		if (sobreMiRepositorio.count() == 0) {
			Persona persona = personaRepositorio.findAll().stream().findFirst().get();

			SobreMi sobreMi = SobreMi.builder()
					.profesion("Fullstack Java")
					.sobreMi("Texto sobre mi")
					.persona(persona)
					.build();

			persona.setSobreMi(sobreMi);

			sobreMiRepositorio.save(sobreMi);
		}
		if (usuarioRepositorio.count() == 0) {
			Usuario usuario = Usuario.builder()
					.usuario(nombreUsuario)
					.password(passwordEncoder.encode(password))
					.build();

			usuarioRepositorio.save(usuario);
		}

		if (!Files.isDirectory(Paths.get(directorio))) {
			archivosServicioImplementacion.crearDirectorio();
		}
	}
}
