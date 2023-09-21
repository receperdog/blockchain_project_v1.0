## Key Components

- **Voting Service:** 
  The core of our system, handling interactions between components and operations related to voting.
  
- **Blockchain Controller:**
  Exposes the blockchain to the external world through a REST API.
  
- **Voting Controller:**
  Offers voting functionalities via a REST API.
  
- **Consensus Protocol:**
  Contains static methods used to validate blocks and to ensure network consensus.
  
- **Transaction:**
  Represents a vote transaction.
  
- **Blockchain:**
  Represents the entire blockchain, maintaining a list of blocks.
  
## Architecture & Design

- **Modular Architecture:**
  Adheres to the single responsibility principle.
  
- **Efficient Synchronization:**
  Employs the `@Scheduled` annotation to trigger regular synchronization processes for consistency.
  
- **Security Measures:**
  Handles Cross-Origin Resource Sharing (CORS) to prevent cross-origin attacks.
  
- **Private Blockchain Network:**
  Balances transparency and security.
  
## Contributors

- [Recep Erdoğan](https://github.com/receperdog)
- [Other Contributors](https://github.com/receperdog/blockchain_project_v1.0/graphs/contributors)

## License

This project is under the MIT license - for more information see the [LICENSE.md](LICENSE.md) file.

## Links

- [Project Page](https://github.com/receperdog/blockchain_project_v1.0)
- [Demonstration Video](https://www.youtube.com/watch?v=8QTwxibqGJY)

## Acknowledgments

We extend our heartfelt thanks to everyone who made this project possible. For inquiries or suggestions, please feel free to contact us.
