import { Client } from "./client.model";

interface ITargetObject {
  targetId?: number;
  name?: string;
  client?: Client;
  objectType?: ObjectType
}

interface ITargetObjectRequest {
  targetId?: number;
  name?: string;
  clientId?: number;
  objectType?: ObjectType
}

interface IObjectType {
  codeTypeId?: number;
  nameType?: string;
}

export class TargetObject implements ITargetObject {
  constructor(
  public targetId?: number,
  public name?: string,
  public client?: Client,
  public objectType?: ObjectType
  ) {}
}

export class TargetObjectRequest implements ITargetObjectRequest {
  constructor(
  public targetId?: number,
  public name?: string,
  public clientId?: number,
  public objectType?: ObjectType
  ) {}
}

export class ObjectType implements IObjectType {
  constructor(
    public codeTypeId?: number,
    public nameType?: string
  ) {}
}
