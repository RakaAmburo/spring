database/
├── domain/
│   └── user/
│       ├── User.java (record)
│       ├── UserRepository.java (interface)
│       └── UserDomainService.java
│
├── application/
│   ├── ports/
│   │   ├── input/
│   │   │   └── UserService.java (interface)
│   │   └── output/
│   │       └── UserRepository.java (interface)
│   └── services/
│       └── UserServiceImpl.java
│
└── infrastructure/
    ├── persistence/
    │   └── user/
    │       ├── UserEntity.java
    │       ├── UserEntityMapper.java (interface)
    │       └── UserJpaRepository.java (interface)
    ├── controllers/
    │   └── user/
    │       ├── UserController.java
    │       ├── UserRequest.java
    │       ├── UserResponse.java
    │       └── UserRequestMapper.java (interface)
    └── config/
        └── ApplicationConfig.java