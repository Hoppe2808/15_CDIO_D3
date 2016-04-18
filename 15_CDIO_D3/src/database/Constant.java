package database;

//erstat konstanterne nedenfor

public abstract class Constant
{
	public static final String
		server					= "ec2-52-30-89-247.eu-west-1.compute.amazonaws.com",  // database-serveren
		database				= "grp15",  //"jdbcdatabase", // navnet paa din database = dit studienummer
		username				= "grp15", // dit brugernavn = dit studienummer 
		password				= "grp15"; // dit password som du har valgt til din database
	
	public static final int
		port					= 3306;
}
