package com.fixtab.app.mappers;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.db.activities.RequestModel;
import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.EditRequestRepairRequest;
import com.fixtab.app.models.requests.RequestRepairRequest;
import com.fixtab.app.models.responses.ActivityResponse;
import com.fixtab.app.models.responses.EmployeeResponse;
import com.fixtab.app.models.responses.RequestRepairResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-31T21:42:59+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class RequestRepairMapperImpl implements RequestRepairMapper {

    @Override
    public RequestRepairResponse toResponse(RequestModel requestModel) {
        if ( requestModel == null ) {
            return null;
        }

        RequestRepairResponse.RequestRepairResponseBuilder requestRepairResponse = RequestRepairResponse.builder();

        requestRepairResponse.requestId( requestModel.getRequestId() );
        requestRepairResponse.description( requestModel.getDescription() );
        requestRepairResponse.result( requestModel.getResult() );
        requestRepairResponse.requestCancelled( requestModel.isRequestCancelled() );
        requestRepairResponse.openDate( requestModel.getOpenDate() );
        requestRepairResponse.progressDate( requestModel.getProgressDate() );
        requestRepairResponse.endDate( requestModel.getEndDate() );
        requestRepairResponse.targetObject( requestModel.getTargetObject() );
        requestRepairResponse.manager( employeeModelToEmployeeResponse( requestModel.getManager() ) );
        requestRepairResponse.status( requestModel.getStatus() );
        requestRepairResponse.activity( activityModelListToActivityResponseList( requestModel.getActivity() ) );

        return requestRepairResponse.build();
    }

    @Override
    public RequestModel toEntity(RequestRepairRequest requestRepairRequest) {
        if ( requestRepairRequest == null ) {
            return null;
        }

        RequestModel.RequestModelBuilder<?, ?> requestModel = RequestModel.builder();

        requestModel.manager( requestRepairRequestToEmployeeModel( requestRepairRequest ) );
        requestModel.targetObject( requestRepairRequestToTargetObjectModel( requestRepairRequest ) );
        requestModel.description( requestRepairRequest.getDescription() );

        return requestModel.build();
    }

    @Override
    public RequestModel toEntity(EditRequestRepairRequest editRequestRepairRequest) {
        if ( editRequestRepairRequest == null ) {
            return null;
        }

        RequestModel.RequestModelBuilder<?, ?> requestModel = RequestModel.builder();

        if ( editRequestRepairRequest.getRequestId() != null ) {
            requestModel.requestId( editRequestRepairRequest.getRequestId() );
        }
        requestModel.description( editRequestRepairRequest.getDescription() );
        requestModel.result( editRequestRepairRequest.getResult() );
        requestModel.requestCancelled( editRequestRepairRequest.isRequestCancelled() );
        requestModel.openDate( editRequestRepairRequest.getOpenDate() );
        requestModel.targetObject( editRequestRepairRequest.getTargetObject() );
        requestModel.status( editRequestRepairRequest.getStatus() );

        return requestModel.build();
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

    protected ActivityResponse activityModelToActivityResponse(ActivityModel activityModel) {
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

    protected List<ActivityResponse> activityModelListToActivityResponseList(List<ActivityModel> list) {
        if ( list == null ) {
            return null;
        }

        List<ActivityResponse> list1 = new ArrayList<ActivityResponse>( list.size() );
        for ( ActivityModel activityModel : list ) {
            list1.add( activityModelToActivityResponse( activityModel ) );
        }

        return list1;
    }

    protected EmployeeModel requestRepairRequestToEmployeeModel(RequestRepairRequest requestRepairRequest) {
        if ( requestRepairRequest == null ) {
            return null;
        }

        EmployeeModel.EmployeeModelBuilder<?, ?> employeeModel = EmployeeModel.builder();

        if ( requestRepairRequest.getManagerId() != null ) {
            employeeModel.employeeId( requestRepairRequest.getManagerId() );
        }

        return employeeModel.build();
    }

    protected TargetObjectModel requestRepairRequestToTargetObjectModel(RequestRepairRequest requestRepairRequest) {
        if ( requestRepairRequest == null ) {
            return null;
        }

        TargetObjectModel.TargetObjectModelBuilder<?, ?> targetObjectModel = TargetObjectModel.builder();

        if ( requestRepairRequest.getTargetObjectId() != null ) {
            targetObjectModel.targetId( requestRepairRequest.getTargetObjectId() );
        }

        return targetObjectModel.build();
    }
}
