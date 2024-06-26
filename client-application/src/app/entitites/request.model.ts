import { Activity, ActivityRequest } from "./activity.model";
import { Employee } from "./employee-model";
import { TargetObject } from "./object.model";
import { Status } from "./result.model";

interface IRequest {
  requestId?: number;
  description?: string;
  result?: string;
  requestCancelled?: boolean;
  openDate?: string;
  progressDate?: string;
  endDate?: string;
  targetObject?: TargetObject;
  manager?: Employee;
  status?: Status;
  activity?: Activity[];
}

interface IRequestRepairRequest {
  description?: string;
  targetObjectId?: number;
  managerId?: number;
  activities?: ActivityRequest[];
}

export class Request implements IRequest {
  constructor(
  public requestId?: number,
  public description?: string,
  public result?: string,
  public requestCancelled?: boolean,
  public openDate?: string,
  public progressDate?: string,
  public endDate?: string,
  public targetObject?: TargetObject,
  public manager?: Employee,
  public status?: Status,
  public activity?: Activity[]
  ) {}
}

export class RequestRepairRequest implements IRequestRepairRequest {
  constructor(
  public description?: string,
  public targetObjectId?: number,
  public managerId?: number,
  public activities?: ActivityRequest[]
  ) {}
}
