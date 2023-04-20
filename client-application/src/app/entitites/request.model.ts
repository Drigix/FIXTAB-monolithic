import { Activity } from "./activity.model";
import { Employee } from "./employee-model";
import { TargetObject } from "./object.model";
import { Result } from "./result.model";

interface IRequest {
  requestId?: number;
  description?: string;
  status?: string;
  requestCancelled?: boolean;
  openDate?: string;
  progressDate?: string;
  endDate?: string;
  targetObject?: TargetObject;
  manager?: Employee;
  result?: Result;
  activity?: Activity[];
}

interface IRequestRepairRequest {
  description?: string;
  targetObjectId?: number;
  managerId?: number;
  activities?: Activity[];
}

export class Request implements IRequest {
  constructor(
  public requestId?: number,
  public description?: string,
  public status?: string,
  public requestCancelled?: boolean,
  public openDate?: string,
  public progressDate?: string,
  public endDate?: string,
  public targetObject?: TargetObject,
  public manager?: Employee,
  public result?: Result,
  public activity?: Activity[]
  ) {}
}

export class RequestRepairRequest implements IRequestRepairRequest {
  constructor(
  public description?: string,
  public targetObjectId?: number,
  public managerId?: number,
  public activities?: Activity[]
  ) {}
}
