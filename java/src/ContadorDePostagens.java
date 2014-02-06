/**
 * Created by felipe on 31/01/14.
 */
public class ContadorDePostagens {

    private static ContadorDePostagens instance;
    private Integer quantidadePostagens = 0;

    private ContadorDePostagens() {

    }

    public static synchronized ContadorDePostagens getInstance() {
        if (instance == null) {
            instance = new ContadorDePostagens();
        }
        return instance;
    }

    public void adicionaPostagem() {
        this.quantidadePostagens++;
    }

    public Integer getQuantidadePostagens() {
        return this.quantidadePostagens;
    }
}
