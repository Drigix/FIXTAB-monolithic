package com.fixtab.app.mappers;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.db.activities.RequestModel;
import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.ActivityRequest;
import com.fixtab.app.models.requests.RequestRepairRequest;
import com.fixtab.app.models.responses.ActivityResponse;
import com.fixtab.app.models.responses.RequestRepairResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T19:50:25+0200",
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
        requestRepairResponse.status( requestModel.getStatus() );
        requestRepairResponse.requestCancelled( requestModel.isRequestCancelled() );
        requestRepairResponse.openDate( requestModel.getOpenDate() );
        requestRepairResponse.progressDate( requestModel.getProgressDate() );
        requestRepairResponse.endDate( requestModel.getEndDate() );
        requestRepairResponse.targetObject( requestModel.getTargetObject() );
        requestRepairResponse.manager( requestModel.getManager() );
        requestRepairResponse.result( requestModel.getResult() );
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
        requestModel.activity( mapActivityRequestsToActivityModels( requestRepairRequest.getActivities() ) );
        requestModel.description( requestRepairRequest.getDescription() );

        return requestModel.build();
    }

    @Override
    public ActivityModel activityRequestToActivityModel(ActivityRequest activity) {
        if ( activity == null ) {
            return null;
        }

        ActivityModel.ActivityModelBuilder activityModel = ActivityModel.builder();

        if ( activity.getSequenceNumber() != null ) {
            activityModel.sequenceNumber( activity.getSequenceNumber() );
        }
        activityModel.description( activity.getDescription() );
        activityModel.activityType( activity.getActivityType() );

        return activityModel.build();
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
        activityResponse.status( activityModel.getStatus() );
        activityResponse.createDate( activityModel.getCreateDate() );
        activityResponse.statusUpateDate( activityModel.getStatusUpateDate() );
        activityResponse.activityType( activityModel.getActivityType() );
        activityResponse.employee( activityModel.getEmployee() );
        activityResponse.result( activityModel.getResult() );

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
