package com.fixtab.app.mappers;

import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.CreateEmployeeRequest;
import com.fixtab.app.models.requests.EditEmployeeRequest;
import com.fixtab.app.models.responses.EmployeeResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-15T20:28:27+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeResponse toResponse(EmployeeModel employeeModel) {
        if ( employeeModel == null ) {
            return null;
        }

        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeResponse.setEmployeeId( employeeModel.getEmployeeId() );
        employeeResponse.setName( employeeModel.getName() );
        employeeResponse.setSurname( employeeModel.getSurname() );
        employeeResponse.setPhoneNumber( employeeModel.getPhoneNumber() );
        employeeResponse.setEmail( employeeModel.getEmail() );
        employeeResponse.setBirthDate( employeeModel.getBirthDate() );
        employeeResponse.setGender( employeeModel.getGender() );
        employeeResponse.setPesel( employeeModel.getPesel() );
        employeeResponse.setRoleId( employeeModel.getRoleId() );

        employeeResponse.setFullName( employeeModel.getName() + " " + employeeModel.getSurname() );

        return employeeResponse;
    }

    @Override
    public EmployeeModel toEntity(CreateEmployeeRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        EmployeeModel.EmployeeModelBuilder<?, ?> employeeModel = EmployeeModel.builder();

        employeeModel.name( employeeRequest.getName() );
        employeeModel.surname( employeeRequest.getSurname() );
        employeeModel.phoneNumber( employeeRequest.getPhoneNumber() );
        employeeModel.email( employeeRequest.getEmail() );
        employeeModel.birthDate( employeeRequest.getBirthDate() );
        employeeModel.gender( employeeRequest.getGender() );
        employeeModel.pesel( employeeRequest.getPesel() );
        employeeModel.roleId( employeeRequest.getRoleId() );

        return employeeModel.build();
    }

    @Override
    public EmployeeModel toEntity(EditEmployeeRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        EmployeeModel.EmployeeModelBuilder<?, ?> employeeModel = EmployeeModel.builder();

        if ( employeeRequest.getEmployeeId() != null ) {
            employeeModel.employeeId( employeeRequest.getEmployeeId() );
        }
        employeeModel.name( employeeRequest.getName() );
        employeeModel.surname( employeeRequest.getSurname() );
        employeeModel.phoneNumber( employeeRequest.getPhoneNumber() );
        employeeModel.email( employeeRequest.getEmail() );
        employeeModel.birthDate( employeeRequest.getBirthDate() );
        employeeModel.gender( employeeRequest.getGender() );
        employeeModel.pesel( employeeRequest.getPesel() );
        employeeModel.roleId( employeeRequest.getRoleId() );

        return employeeModel.build();
    }
}
