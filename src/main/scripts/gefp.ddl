
    create table Cell (
        id int8 not null,
        plan_id int8,
        runway_id int8,
        stage_id int8,
        primary key (id)
    );

    create table Checkpoint (
        id int8 not null,
        description varchar(255),
        primary key (id)
    );

    create table Department (
        id int8 not null,
        name varchar(255),
        plan_id int8,
        primary key (id)
    );

    create table Plan (
        id int8 not null,
        name varchar(255),
        publishedDate timestamp,
        primary key (id)
    );

    create table Runway (
        id int8 not null,
        name varchar(255),
        primary key (id)
    );

    create table Stage (
        id int8 not null,
        name varchar(255),
        primary key (id)
    );

    create table authorities (
        user_id int8 not null,
        role varchar(255)
    );

    create table cell_checkpoints (
        cell_id int8 not null,
        checkpoint_id int8 not null
    );

    create table department_plans (
        department_id int8 not null,
        plan_id int8 not null
    );

    create table plan_runways (
        plan_id int8 not null,
        runway_id int8 not null,
        runway_order int4 not null,
        primary key (plan_id, runway_order)
    );

    create table plan_stages (
        plan_id int8 not null,
        stage_id int8 not null,
        stage_order int4 not null,
        primary key (plan_id, stage_order)
    );

    create table user_checkpoints (
        user_id int8 not null,
        checkpoint_id int8 not null,
        primary key (user_id, checkpoint_id)
    );

    create table users (
        id int8 not null,
        accessKey varchar(255),
        cin varchar(255),
        email varchar(255),
        enabled boolean not null,
        firstName varchar(255),
        lastName varchar(255),
        password varchar(255),
        username varchar(255),
        major_id int8,
        plan_id int8,
        primary key (id)
    );

    alter table cell_checkpoints 
        add constraint UK_1bq0aejed37fc3yhyfoca8qqu unique (checkpoint_id);

    alter table department_plans 
        add constraint UK_huvk9td7lxurmvpn2a3bji8a3 unique (plan_id);

    alter table plan_runways 
        add constraint UK_lef4smjv3ev3yh08afagtxxsv unique (runway_id);

    alter table plan_stages 
        add constraint UK_b7topryl89bhic8opolhi1feg unique (stage_id);

    alter table user_checkpoints 
        add constraint UK_to4y5oilg0c79s52l5dh3u9tk unique (checkpoint_id);

    alter table Cell 
        add constraint FK_9cihd4a0k4cwgdlk744o7ldou 
        foreign key (plan_id) 
        references Plan;

    alter table Cell 
        add constraint FK_s0a37aw4m7ihpmy3us4pdmbl0 
        foreign key (runway_id) 
        references Runway;

    alter table Cell 
        add constraint FK_3nhk7mlc8idanh84miu6bb3fa 
        foreign key (stage_id) 
        references Stage;

    alter table Department 
        add constraint FK_h20hx893s2rqmwejm7writq5o 
        foreign key (plan_id) 
        references Plan;

    alter table authorities 
        add constraint FK_s21btj9mlob1djhm3amivbe5e 
        foreign key (user_id) 
        references users;

    alter table cell_checkpoints 
        add constraint FK_1bq0aejed37fc3yhyfoca8qqu 
        foreign key (checkpoint_id) 
        references Checkpoint;

    alter table cell_checkpoints 
        add constraint FK_ppgeqpgh9b3dyqdofg4h00bm8 
        foreign key (cell_id) 
        references Cell;

    alter table department_plans 
        add constraint FK_huvk9td7lxurmvpn2a3bji8a3 
        foreign key (plan_id) 
        references Plan;

    alter table department_plans 
        add constraint FK_1ua4dr9e77minpmjjwcbhgsk6 
        foreign key (department_id) 
        references Department;

    alter table plan_runways 
        add constraint FK_lef4smjv3ev3yh08afagtxxsv 
        foreign key (runway_id) 
        references Runway;

    alter table plan_runways 
        add constraint FK_qguy17dy50abbbqck5pckgb4a 
        foreign key (plan_id) 
        references Plan;

    alter table plan_stages 
        add constraint FK_b7topryl89bhic8opolhi1feg 
        foreign key (stage_id) 
        references Stage;

    alter table plan_stages 
        add constraint FK_4aeev79mdax326wtl8f06efil 
        foreign key (plan_id) 
        references Plan;

    alter table user_checkpoints 
        add constraint FK_to4y5oilg0c79s52l5dh3u9tk 
        foreign key (checkpoint_id) 
        references Checkpoint;

    alter table user_checkpoints 
        add constraint FK_t1q6bdp8kteiy9pt35cy7pug5 
        foreign key (user_id) 
        references users;

    alter table users 
        add constraint FK_q37jte7r1ptl16arimkk23y1h 
        foreign key (major_id) 
        references Department;

    alter table users 
        add constraint FK_km7rd8sgwa1qls24gkxoh2b2i 
        foreign key (plan_id) 
        references Plan;

    create sequence hibernate_sequence;
