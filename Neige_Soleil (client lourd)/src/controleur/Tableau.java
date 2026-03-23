package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau  extends AbstractTableModel
{
	
	private Object [][] donnees ; //matrice des données
	private String [] entetes ; // nom des colonnes
	public Tableau (Object [][] donnees, String [] entetes) {
		this.donnees = donnees;
		this.entetes=entetes;
	}

	@Override
	public int getRowCount() {
	return this.donnees.length; // nombre de lignes
	}

	@Override
	public int getColumnCount() {
	return this.entetes.length;//nombres de colonnes
	}

	@Override
	public Object getValueAt(int i, int j) {
	return this.donnees[i][j]; //retourne l'element de la table [i,j]
	}

	@Override
	public String getColumnName(int i) {
	return this.entetes[i];
		
	}
	
	public void setDonnees(Object [] [] matrice ) {
		this.donnees=matrice;
		this.fireTableDataChanged();//qui actualise les données
	}
}


