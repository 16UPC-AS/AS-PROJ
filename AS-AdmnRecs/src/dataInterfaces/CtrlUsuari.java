package dataInterfaces;

import java.util.List;

import model.Usuari;

public interface CtrlUsuari {

	public Usuari getByPK(String username);

	public List<Usuari> getAll();

}
