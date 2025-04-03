package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "Maze File"); //accepts maze 
        options.addOption("p", true, "Path"); //accepts solution to verify

        CommandLineParser parser = new DefaultParser();
        String mazeFile = new String();
        try {
            //parse the command line arguments
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("i")){
                mazeFile = cmd.getOptionValue("i");
            }
            else{
                logger.error("No maze was uploaded.");
                System.exit(1); //failure
            }

            logger.info("** Starting Maze Runner");

            try {
                logger.debug("**** Reading the maze from file " + mazeFile);
                Maze m = new Maze(mazeFile);

                if (cmd.hasOption("p")){ //verify solution if -p flag
                    String pathToCheck = cmd.getOptionValue("p");
                    PathRunner pathRunner = new PathRunner(m.getEntryRow());
                    if (pathRunner.isCorrectPath(pathToCheck, m)){
                        System.out.println("correct path");
                    }
                    else{
                        System.out.println("incorrect path");
                    }
                }
                else{ //no -p flag means solve maze for user 
                    RightHandSolver solver = new RightHandSolver(m.getEntryRow());
                    System.out.println(solver.solveMaze(m));
                }
            }catch(Exception e) {
                logger.error("/!\\ An error has occured /!\\");
                System.exit(1);
            }    

        }catch (ParseException e) {
            //something went wrong
            logger.error("Parsing failed.  Reason: " + e.getMessage());
            System.exit(1); //failure
        }
        
        logger.debug("**** Computing path");
        logger.debug("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
