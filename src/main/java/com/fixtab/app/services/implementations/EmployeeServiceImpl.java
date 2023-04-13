package com.fixtab.app.services.implementations;

import com.fixtab.app.mappers.EmployeeMapper;
import com.fixtab.app.infrastructure.PasswordHelperMethods;
import com.fixtab.app.models.db.UserModel;
import com.fixtab.app.models.db.customers.PasswordModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.db.employees.EmployeeRoleModel;
import com.fixtab.app.models.requests.ChangePasswordRequest;
import com.fixtab.app.models.requests.CreateEmployeeRequest;
import com.fixtab.app.models.requests.EditEmployeeRequest;
import com.fixtab.app.models.responses.EmployeeResponse;
import com.fixtab.app.respositories.EmployeeRepository;
import com.fixtab.app.respositories.PasswordRepository;
import com.fixtab.app.services.interfaces.EmployeeRoleService;
import com.fixtab.app.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordRepository passwordRepository;

    private final EmployeeRoleService employeeRoleService;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeResponse> getAllNotDeletedEmployees() {
        List<EmployeeModel> employeeList = employeeRepository.findAllByDeletedFalse();
        return  employeeList.stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

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
                .role(employeeRoleService.getEmployeeRole(employee.get().getRoleId()).get())
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
        EmployeeModel employee = employeeMapper.toEntity(request);
        employee.setDeleted(false);

        employee = employeeRepository.save(employee);

        // send newly generated password to email
        String salt = PasswordHelperMethods.generateBase64Salt();
        String password = PasswordHelperMethods.generateRandomPassword();
        PasswordModel passwordModel = PasswordModel.builder()
                .salt(salt)
                .employeeId(employee.getEmployeeId())
                .passwordHash(PasswordHelperMethods.passwordToHash(password, employee.getEmail(), salt))
                .build();
        passwordRepository.save(passwordModel);
        return password;
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<EmployeeModel> employee = employeeRepository.findByEmail(email);
        Optional<PasswordModel> password = passwordRepository.findByEmployeeId(employee.get().getEmployeeId());

        String requestHashPassword = PasswordHelperMethods.passwordToHash(changePasswordRequest.getOldPassword(), employee.get().getEmail(), password.get().getSalt());
        if(!requestHashPassword.equals(password.get().getPasswordHash())) {
            throw new RuntimeException();
        }
        String newHashPassword = PasswordHelperMethods.passwordToHash(changePasswordRequest.getPassword(), employee.get().getEmail(),  password.get().getSalt());
        password.get().setPasswordHash(newHashPassword);
        passwordRepository.save(password.get());
    }


    @Override
    public EmployeeResponse editEmployee(EditEmployeeRequest employeeRequest) {
        EmployeeModel employee = employeeMapper.toEntity(employeeRequest);
        Optional<EmployeeModel> existEmployee = employeeRepository.findById(employee.getEmployeeId());
        String editBy = SecurityContextHolder.getContext().getAuthentication().getName();
        employee.setDeleted(existEmployee.get().getDeleted());
        employee.setCreatedDate(existEmployee.get().getCreatedDate());
        employee.setCreatedBy(existEmployee.get().getCreatedBy());
        employee.setModifiedBy(editBy);
        employeeRepository.save(employee);

        return employeeMapper.toResponse(employee);
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
