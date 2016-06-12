package dataInterfaces;

import java.util.List;

import model.Recurs;

public interface CtrlRecurs {

	public Recurs getByPK(String nom);

	public List<Recurs> getAll();

}
