-- public.activitytype definition

-- Drop table

-- DROP TABLE public.activitytype;

CREATE TABLE public.activitytype (
	typeid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	typename varchar NULL,
	CONSTRAINT activitytype_pkey PRIMARY KEY (typeid)
);


-- public.address definition

-- Drop table

-- DROP TABLE public.address;

CREATE TABLE public.address (
	street varchar NULL,
	city varchar NULL,
	postcode varchar NULL,
	flatnumber varchar NULL,
	streetnumber varchar NULL,
	country varchar NULL,
	addressid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	CONSTRAINT address_pkey PRIMARY KEY (addressid)
);


-- public.employeeroles definition

-- Drop table

-- DROP TABLE public.employeeroles;

CREATE TABLE public.employeeroles (
	roleid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	rolename varchar NOT NULL,
	CONSTRAINT employeeroles_pkey PRIMARY KEY (roleid)
);


-- public.objecttype definition

-- Drop table

-- DROP TABLE public.objecttype;

CREATE TABLE public.objecttype (
	codetypeid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	name_type varchar NULL,
	CONSTRAINT objecttype_pkey PRIMARY KEY (codetypeid)
);


-- public.statusdictionary definition

-- Drop table

-- DROP TABLE public.statusdictionary;

CREATE TABLE public.statusdictionary (
	statusid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	status varchar(60) NULL,
	CONSTRAINT result_pkey PRIMARY KEY (statusid)
);


-- public.client definition

-- Drop table

-- DROP TABLE public.client;

CREATE TABLE public.client (
	"name" varchar NOT NULL,
	surname varchar NOT NULL,
	phonenumber varchar NULL,
	email varchar NULL,
	birthdate date NULL,
	gender varchar NULL,
	pesel varchar NULL,
	clientid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	addressid int4 NULL,
	createdby varchar(255) NULL,
	createddate timestamp(6) NULL,
	deleted bool NULL,
	modifiedby varchar(255) NULL,
	modifieddate timestamp(6) NULL,
	CONSTRAINT client_pkey PRIMARY KEY (clientid),
	CONSTRAINT customer_address_id FOREIGN KEY (addressid) REFERENCES public.address(addressid)
);


-- public.targetobject definition

-- Drop table

-- DROP TABLE public.targetobject;

CREATE TABLE public.targetobject (
	targetid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NULL,
	objecttypeid int4 NULL,
	createdby varchar(255) NULL,
	createddate timestamp(6) NULL,
	deleted bool NULL,
	modifiedby varchar(255) NULL,
	modifieddate timestamp(6) NULL,
	clientid int4 NULL,
	CONSTRAINT targetobject_pkey PRIMARY KEY (targetid),
	CONSTRAINT fkctuska3cy3bo48meu7rxw879j FOREIGN KEY (clientid) REFERENCES public.client(clientid),
	CONSTRAINT targetobject_object_key FOREIGN KEY (objecttypeid) REFERENCES public.objecttype(codetypeid)
);


-- public.employees definition

-- Drop table

-- DROP TABLE public.employees;

CREATE TABLE public.employees (
	"name" varchar NOT NULL,
	surname varchar NOT NULL,
	phonenumber varchar NULL,
	email varchar NULL,
	birthdate date NULL,
	gender varchar NULL,
	pesel varchar NULL,
	employeeid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	targetobjectid int4 NULL,
	roleid int4 NULL,
	deleted bool NULL DEFAULT false,
	createddate timestamp(6) NULL,
	modifieddate timestamp(6) NULL,
	createdby varchar(255) NULL,
	modifiedby varchar(255) NULL,
	CONSTRAINT employees_pkey PRIMARY KEY (employeeid),
	CONSTRAINT employee_role_key FOREIGN KEY (roleid) REFERENCES public.employeeroles(roleid),
	CONSTRAINT employee_target_object_key FOREIGN KEY (targetobjectid) REFERENCES public.targetobject(targetid)
);


-- public.passwords definition

-- Drop table

-- DROP TABLE public.passwords;

CREATE TABLE public.passwords (
	employeeid int4 NOT NULL,
	passwordhash varchar NULL,
	salt varchar NULL,
	createdate date NULL DEFAULT now(),
	isactive bool NULL DEFAULT false,
	idpassword int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	CONSTRAINT passwords_pkey PRIMARY KEY (idpassword),
	CONSTRAINT password_employee_key FOREIGN KEY (employeeid) REFERENCES public.employees(employeeid)
);


-- public.requests definition

-- Drop table

-- DROP TABLE public.requests;

CREATE TABLE public.requests (
	requestid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	description varchar NULL,
	requestcancelled bool NULL DEFAULT false,
	targetobjectid int4 NULL,
	managerid int4 NULL,
	opendate timestamp(6) NULL,
	progressdate timestamp(6) NULL,
	enddate timestamp(6) NULL,
	statusid int4 NULL,
	createdby varchar(255) NULL,
	createddate timestamp(6) NULL,
	deleted bool NULL,
	modifiedby varchar(255) NULL,
	modifieddate timestamp(6) NULL,
	"result" varchar(255) NULL,
	CONSTRAINT requests_pkey PRIMARY KEY (requestid),
	CONSTRAINT request_status_id FOREIGN KEY (statusid) REFERENCES public.statusdictionary(statusid),
	CONSTRAINT requests_manager_key FOREIGN KEY (managerid) REFERENCES public.employees(employeeid),
	CONSTRAINT requests_object_key FOREIGN KEY (targetobjectid) REFERENCES public.targetobject(targetid)
);


-- public.activities definition

-- Drop table

-- DROP TABLE public.activities;

CREATE TABLE public.activities (
	activityid int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	sequencenumber int4 NULL,
	description varchar NULL,
	cancelled bool NULL,
	activitytypeid int4 NOT NULL,
	createdate date NULL,
	statusupdatedate date NULL,
	statusid int4 NULL,
	employeeid int4 NULL,
	requestid int4 NULL,
	createdby varchar(255) NULL,
	createddate timestamp(6) NULL,
	deleted bool NULL,
	modifiedby varchar(255) NULL,
	modifieddate timestamp(6) NULL,
	statusupatedate timestamp(6) NULL,
	"result" varchar(255) NULL,
	CONSTRAINT activities_pkey PRIMARY KEY (activityid),
	CONSTRAINT activities_activitytype_key FOREIGN KEY (activitytypeid) REFERENCES public.activitytype(typeid),
	CONSTRAINT activity_employee_key FOREIGN KEY (employeeid) REFERENCES public.employees(employeeid),
	CONSTRAINT activity_status_id FOREIGN KEY (statusid) REFERENCES public.statusdictionary(statusid),
	CONSTRAINT fkgikkmjkgxf59gn84m17ostbdo FOREIGN KEY (requestid) REFERENCES public.requests(requestid)
);