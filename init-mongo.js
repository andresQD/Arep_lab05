db.createUser(
        {
            user: "andrew",
            pwd: "arep2020",
            roles: [
                {
                    role: "readWrite",
                    db: "DataBaseArep"
                }
            ]
        }
);