package com.company.Service;

import com.company.model.consults.Consult;
import com.company.model.consults.Procedure;
import com.company.model.workers.Medic;
import com.company.repository.ConsultRepository;
import com.company.repository.MedicRepository;
import com.company.repository.NurseRepository;
import com.company.repository.ProcedureRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private static EmployeeService instance=new EmployeeService();
    private MedicRepository medicRepo = new MedicRepository();
    private NurseRepository nurseRepo = new NurseRepository();
    private ConsultRepository consultRepo = new ConsultRepository();
    private ProcedureRepository procedureRepo = new ProcedureRepository();
    private EmployeeService()
    {

    }
    public void addMedic(String name, String dep, long cnp)
    {
        medicRepo.addMedic(name, dep, cnp);
    }
    public void addNurse(String name, long cnp, String department)
    {
        nurseRepo.addNurse(name, cnp, department);
    }

    public void addConsult(long medicCNP, long patientCNP)
    {
        consultRepo.addConsult(medicCNP,patientCNP);
    }
    public List<Medic> viewMedicsByDepartment(String dep)
    {
        return medicRepo.viewByDep(dep);
    }
    public List<Consult>viewConsultsByMedic(long cnp){
        return consultRepo.viewByMedic(cnp);
    }

    public List<Medic> viewMedicProcedure(long id)
    {
        List<Long> m = procedureRepo.getProcedureMedics(id);
        return medicRepo.getMedicsById(m);
    }

}
