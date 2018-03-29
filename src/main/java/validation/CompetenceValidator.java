package validation;

import entity.Competence;
import entity.CompetencePriorityType;
import exception.CompetenceScaleException;
import exception.ObligatoryCompetenceException;

import java.util.List;

public class CompetenceValidator {

    public boolean checkLevel(int level) throws CompetenceScaleException {
        if (level<1 || level>10) { throw new CompetenceScaleException("from 1 to 10 only!"); }
        else
            return true;
    }

    public boolean checkObligatory(List<Competence> list) throws ObligatoryCompetenceException {
        for (int i=0; i<list.size(); i++)
        {
            if (list.get(i).getPriority().equals(CompetencePriorityType.OBLIGATORY))
                return true;
        }
        throw new ObligatoryCompetenceException("At least one competence should have OBLIGATORY priority");
    }
}
