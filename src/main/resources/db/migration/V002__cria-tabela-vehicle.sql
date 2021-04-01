create table vehicle (
id bigint not null auto_increment,
name text not null,
`desc` text not null,
vehicle_type bigint not null,
plate text,

primary key (id)
);

alter table vehicle add constraint fk_vehicle_vehicle_type
foreign key (vehicle_type) references vehicle_type (id);