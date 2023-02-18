# Banking-Software-Interface
With my knowledge and learning in JAVA and its web services, I developed a banking simulation interface.

### How It Works
Upon running, the user can register for their account or log into an existing one. 
All data is stored in an Oracle database, where SQL querries are used to insert the information at the time of registration and fetch the relevant information at the time of verification during login.

After logging in, users have the option to deposit money, withdraw money, and transfer money to another account with full password authentication. On the backend with JAVA servlet, I performed relevant CRUD operations in SQL with the Oracle database.

While this is not in collaboration in real banks, it effectively imitates the virtual banking environment with full web services. I have also implemented User Cookies and HTTP Sessions to ensure that one user's home page cannot be accessed in another browser without a login.

This is inspired by the School Database System Project that I made using the exact same tech stack.
Oftentimes, the most challenging aspects of the project is to manage dependencies, especially with JAVA servlet API, the Apache Tomcat Server API, and many more to manage. However, once that is down, it becomes really simple to develop such full stack applications.


### Usage
For usage, the code in the main branch is configured for the orginal workspace in the Eclipse Ide.
However, I have added and updated a <em>vscode</em> branch that contains the code without specific workspace configurations.
You will need to do:
<ol>
  <li>ServletAPI.jar</li>
  <li>TomcatAPI.jar</li>
</ol>

to your workspace dependencies. The ojdbc.jar is already present in the workspace dependencies.
