package com.transporte.app.services;

import java.util.List;

import com.transporte.app.entity.Usuario;

public interface UsuarioService {

    public Usuario saveUsuario(Usuario registroDTO);
	
	public List<Usuario> getAllUsuario();
	
	public Usuario login(Usuario usuario);
	
	public Usuario findById(long id) ;
	public Usuario saveUsuarioCliente (Usuario usuario);
}
