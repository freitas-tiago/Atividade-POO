package PetStore;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PetStore1 {

	private ArrayList<Exame> exames;


	public PetStore1( ) {
		this.exames = new ArrayList<Exame>();
	}

	public void adicionaExame(Exame mani) {
		this.exames.add(mani);
	}

	public void listarExame() {
		for(Exame mani: exames) {
			System.out.println(mani.toString());
		}
		System.out.println("Total = " + this.exames.size() + " Exames listados com sucesso!\n");
	}
	
	public void excluirExame(Exame mani) {
		if (this.exames.contains(mani)) {
			this.exames.remove(mani);
			System.out.println("[Exame " + mani.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Exame inexistente!\n");
	}

	public void excluirExame() {
		exames.clear();
		System.out.println("Exames excluidos com sucesso!\n");
	}
	public void gravarExame()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\exame.dat"));
			for(Exame mani: exames) {
				outputStream.writeObject(mani);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarExame() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\exame.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof RaioX)
					this.exames.add((RaioX)obj);
				else if (obj instanceof Sangue)
					this.exames.add((Sangue)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Exames recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		PetStore1 pet  = new PetStore1();

		RaioX felix    = new RaioX("Felix",    3, "Maria");
		RaioX garfield = new RaioX("Garfield", 7, "Maria");
		Sangue rex      = new Sangue("Rex",  2, "Jose");
		Sangue toto     = new Sangue("Toto", 5, "Jose");
		pet.adicionaExame(felix);
		pet.adicionaExame(garfield);
		pet.adicionaExame(rex);
		pet.adicionaExame(toto);
		pet.listarExame();
		pet.gravarExame();
		pet.excluirExame(garfield);
		pet.listarExame();
		pet.excluirExame();
		pet.listarExame();
		pet.recuperarExame();
		pet.listarExame();
	}

}
