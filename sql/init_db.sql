
CREATE table worker(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(1000) CONSTRAINT name_worker_ck CHECK
    (LENGTH(name) >=2) NOT NULL,
    birthday DATE CONSTRAINT birthday_worker_ck CHECK
    (birthday > '1900-01-01'),
    level VARCHAR(15) CONSTRAINT level_worker_ck CHECK
    (level IN ('Trainee','Junior','Middle','Senior')) NOT NULL,
    salary INTEGER CONSTRAINT salary_worker_ck CHECK
    (salary >= 100 AND salary <=100000)
);


CREATE table client(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(1000) CONSTRAINT name_client_ck CHECK
    (LENGTH(name) >=2) NOT NULL
);


CREATE table project(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    start_date DATE,
    finish_date DATE,
    FOREIGN KEY (client_id) REFERENCES client(id)
);


CREATE table project_worker(
    project_id INT NOT NULL,
    worker_id INT NOT NULL,
    PRIMARY KEY ( project_id, worker_id),
    FOREIGN KEY (project_id) REFERENCES project (id),
    FOREIGN KEY (worker_id) REFERENCES worker(id)
);