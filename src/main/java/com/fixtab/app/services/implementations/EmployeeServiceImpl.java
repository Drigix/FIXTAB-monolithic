package com.fixtab.app.services.implementations;

import com.fixtab.app.mappers.EmployeeMapper;
import com.fixtab.app.infrastructure.PasswordHelperMethods;
import com.fixtab.app.models.db.UserModel;
import com.fixtab.app.models.db.customers.PasswordModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.CreateEmployeeRequest;
import com.fixtab.app.models.requests.EditEmployeeRequest;
import com.fixtab.app.models.responses.EmployeeResponse;
import com.fixtab.app.respositories.EmployeeRepository;
import com.fixtab.app.respositories.PasswordRepository;
import com.fixtab.app.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordRepository passwordRepository;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeModel> getAllNotDeletedEmployees() { return employeeRepository.findAllByDeletedFalse(); }

    @Override
    public UserModel getUserModel(String email) {
        Optional<EmployeeModel> employee = employeeRepository.findByEmail(email);
        if (employee.isEmpty())
            return null;
        Optional<PasswordModel> password = passwordRepository.findByEmployeeId(employee.get().getEmployeeId());
        if (password.isEmpty())
            return null;

        UserModel user = UserModel.builder()
                .hashedPassword(password.get().getPasswordHash())
                .role(null)
                .name(employee.get().getName())
                .surname(employee.get().getSurname())
                .email(employee.get().getEmail())
                .build();
        return user;
    }

    @Override
    public String createEmployee(CreateEmployeeRequest request) {

        /* VALIDATORS */
        /* CHECK IF USER EXISTS */

        EmployeeModel employee = EmployeeModel.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .pesel(request.getPesel())
                .deleted(false)
                .build();
        employee = employeeRepository.save(employee);

        // send newly generated password to email
        String salt = PasswordHelperMethods.generateBase64Salt();
        String password = PasswordHelperMethods.generateRandomPassword();
        PasswordModel passwordModel = PasswordModel.builder()
                .salt(salt)
                .employeeId(employee.getEmployeeId())
                .passwordHash(PasswordHelperMethods.passwordToHash(password, employee.getEmail(), salt))
                .build();
        passwordModel = passwordRepository.save(passwordModel);
        return password;
    }

    @Override
    public EmployeeResponse editEmployee(EditEmployeeRequest employeeRequest) {
        EmployeeModel employee = employeeMapper.toEntity(employeeRequest);
        employeeRepository.save(employee);

        EmployeeResponse response = employeeMapper.toResponse(employee);
        return response;
    }


    @Override
    public EmployeeModel loadUserByEmail(String email) {
        Optional<EmployeeModel> employee = employeeRepository.findByEmail(email);
        if (employee.isEmpty()) {
            return null;
        }
        return employee.get();
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Optional<EmployeeModel> employee = employeeRepository.findById(employeeId);
        employee.get().setDeleted(true);
        employeeRepository.save(employee.get());
    }

}
