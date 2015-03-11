import java.io.Serializable;
import java.util.List;

import com.Transpot.Fast.Repositorio.RepositorioUsuario;
import com.Transpot.Fast.model.Usuario;

public class Teste implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
	
	
		RepositorioUsuario ru = new RepositorioUsuario();
		
	List<Usuario> lista	=ru.todos();
		
	
	for (Usuario usuario : lista) {
		
		System.out.println(usuario.toString());
	}
	
	}
}
