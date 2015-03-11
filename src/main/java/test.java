import com.Transpot.Fast.Repositorio.RepositorioAdmin;
import com.Transpot.Fast.model.Admin;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Admin a = new Admin();
		a.setCpf("a");
		a.setNome("a");
		
		RepositorioAdmin rp = new RepositorioAdmin();
		rp.CadastrarUsuario(a);
		

	}

}
