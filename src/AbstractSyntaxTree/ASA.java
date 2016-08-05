package AbstractSyntaxTree;

/*
 * Classe que representa a raiz da Árvore Sintática Abstrata
 */
public abstract class ASA {
	public abstract Object accept(XVisitor visitor);
}
