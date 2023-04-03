interface IEmployee {
  employeeId?: number;
  name?: string;
  surname?: string;
  email?: string;
  phoneNumber?: string;
  birthDate?: string;
  gender?: string;
  pesel?: string;
  addressId?: number;
  targetObjectId?: number;
  roleId?: number;
}

export class Employee implements IEmployee {
  constructor(
   public employeeId?: number,
   public name?: string,
   public surname?: string,
   public email?: string,
   public birthDate?: string,
   public phoneNumber?: string,
   public gender?: string,
   public pesel?: string,
   public addressId?: number,
   public targetObjectId?: number,
   public roleId?: number
  ) {}
}
