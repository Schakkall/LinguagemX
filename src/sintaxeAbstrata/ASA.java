package sintaxeAbstrata;

/*
 * Classe que representa a raiz da �rvore Sint�tica Abstrata
 */
public abstract class ASA {
	public abstract Object accept(XVisitor visitor);
}
