package tempName.client;

public interface IVaegt {

	void addMaaling(int maalingsID, double vaegt, int Id);

	double getMaalingsID(int index);
	double getVaegt(int index);

	int getId(int index);
	int getVaegtArratLaengde();

	String toString(int index);
}
