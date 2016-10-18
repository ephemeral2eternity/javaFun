package factory;
import impl.ImplSolution;
import service.*;

/**
 * Created by Chen Wang on 10/18/2016.
 */
public class SolFactory {
    public static ImplSolution createSolution(int solType)
    {
        ImplSolution sol = null;

        if(solType == 1){
            sol = new SolutionMatrix();
        }else if(solType == 2){
            sol = new SolutionArray();
        }
        else if(solType == 3){
            sol = new SolutionBrute();
        }

        return sol;
    }
}
