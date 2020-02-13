# NC Edu Project




### Database setup 
Copy-Past next commands to create and setup DB environment after installation.

```
sudo -u postgres psql
postgres=# create database nc_project;
postgres=# create user project_user with encrypted password 'password';
postgres=# grant all privileges on database nc_project to project_user;
```

