package com.fixtab.app.mappers;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.ActivityRequest;
import com.fixtab.app.models.requests.EditActivityRequest;
import com.fixtab.app.models.responses.ActivityResponse;
import com.fixtab.app.models.responses.EmployeeResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-31T21:42:59+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class ActivityMapperImpl implements ActivityMapper {

    @Override
    public ActivityResponse toResponse(ActivityModel activityModel) {
        if ( activityModel == null ) {
            return null;
        }

        ActivityResponse.ActivityResponseBuilder activityResponse = ActivityResponse.builder();

        activityResponse.activityId( activityModel.getActivityId() );
        activityResponse.sequenceNumber( activityModel.getSequenceNumber() );
        activityResponse.description( activityModel.getDescription() );
        activityResponse.cancelled( activityModel.isCancelled() );
        activityResponse.result( activityModel.getResult() );
        activityResponse.createDate( activityModel.getCreateDate() );
        activityResponse.statusUpateDate( activityModel.getStatusUpateDate() );
        activityResponse.activityType( activityModel.getActivityType() );
        activityResponse.employee( employeeModelToEmployeeResponse( activityModel.getEmployee() ) );
        activityResponse.status( activityModel.getStatus() );

        return activityResponse.build();
    }

    @Override
    public ActivityModel toEntity(ActivityRequest activityRequest) {
        if ( activityRequest == null ) {
            return null;
        }

        ActivityModel.ActivityModelBuilder activityModel = ActivityModel.builder();

        activityModel.employee( activityRequestToEmployeeModel( activityRequest ) );
        if ( activityRequest.getSequenceNumber() != null ) {
            activityModel.sequenceNumber( activityRequest.getSequenceNumber() );
        }
        activityModel.description( activityRequest.getDescription() );
        activityModel.activityType( activityRequest.getActivityType() );

        return activityModel.build();
    }

    @Override
    public ActivityModel toEntity(EditActivityRequest editActivityRequests) {
        if ( editActivityRequests == null ) {
            return null;
        }

        ActivityModel.ActivityModelBuilder activityModel = ActivityModel.builder();

        if ( editActivityRequests.getActivityId() != null ) {
            activityModel.activityId( editActivityRequests.getActivityId() );
        }
        if ( editActivityRequests.getSequenceNumber() != null ) {
            activityModel.sequenceNumber( editActivityRequests.getSequenceNumber() );
        }
        activityModel.description( editActivityRequests.getDescription() );
        activityModel.cancelled( editActivityRequests.isCancelled() );
        activityModel.result( editActivityRequests.getResult() );
        activityModel.createDate( editActivityRequests.getCreateDate() );
        activityModel.activityType( editActivityRequests.getActivityType() );
        activityModel.employee( editActivityRequests.getEmployee() );
        activityModel.status( editActivityRequests.getStatus() );

        return activityModel.build();
    }

    protected EmployeeResponse employeeModelToEmployeeResponse(EmployeeModel employeeModel) {
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

        return employeeResponse;
    }

    protected EmployeeModel activityRequestToEmployeeModel(ActivityRequest activityRequest) {
        if ( activityRequest == null ) {
            return null;
        }

        EmployeeModel.EmployeeModelBuilder<?, ?> employeeModel = EmployeeModel.builder();

        if ( activityRequest.getEmployeeId() != null ) {
            employeeModel.employeeId( activityRequest.getEmployeeId() );
        }

        return employeeModel.build();
    }
}
