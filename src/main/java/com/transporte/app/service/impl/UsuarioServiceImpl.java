package com.transporte.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.transporte.app.entity.Usuario;
import com.transporte.app.repository.UsuarioRepository;
import com.transporte.app.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository usuarioRepositorio;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario saveUsuario(Usuario objUsuario) {
		Usuario usuario = new Usuario(objUsuario.getNombres(),objUsuario.getApellidos(),
				objUsuario.getUsername(),objUsuario.getClave(), objUsuario.getRol()) ;
				
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public List<Usuario> getAllUsuario() {
		return usuarioRepositorio.findAll();
	}

	@Override
    public Usuario login(Usuario usuario) {
        return usuarioRepositorio.findByUsuarioAndClave(usuario.getUsername(), usuario.getClave());
    }

	@Override
	public Usuario findById(long id) {
	    return usuarioRepositorio.findById(id).get();
	}


}
