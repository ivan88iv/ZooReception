# ZooReception
A demo project used for component and contract testing

## Setup

### Github

<details>
    <summary>Fulfil this section if you do not own a GitHub account:</summary>
    <ol>
        <li>Go to https://github.com/</li>
        <li>Click the Sign Up button</li>
        <li>Enter your email address, password and username</li>
        <li>Enter your verification code received on your email</li>
        <li>Create the account of your choice (most likely a free one ;) )</li>
        <li>You should not be inside your account. Congrats!</li>
    </ol>
</details>

<br>

<details>
    <summary>Do this section if you have not set an authentication token (needed for pushing in the repos later)</summary>
    <ol>
        <li>Log in your account</li>
        <li>Click on your profile image in the top right corner and select Settings</li>
        <li>Select Developer settings - the last option in the left menu</li>
        <li>Select Personal access tokens</li>
        <li>Select Generate new token</li>
        <li>Write some description in the Note field</li>
        <li>Check the repo checkbox</li>
        <li>Press generate token.</li>
        <li>You should now see a special token generated. You need to use it as a password when pushing information to a repo.</li>
        <details>
            <summary>If you do not want to type this for every push do the following steps:</summary>
            <li>Open a command line terminal</li>
            <li>Type git config --global --list</li>
            <li>See what value you have for the key "credential.helper"</li>
            <li>
                We suggest it should be manager-core. 
                If not we recommend executing git config --global credential.helper manager-core.
                This will ensure that after the first time you are asked to type your username and 
                authorization token it will be cached for future purposes.
            </li>
        </details>
    </ol>
</details>

### OpenAPI

We will be needing good support for swagger/openApi standard throughout the workshops.
To do so we can follow two strategies:
<details>
    <summary>Add swagger to your IntelliJ</summary>
    <ol>
        <li>This approach is a little harder to initially setup but it will save you time afterwards!</li>
        <li>Open your IntelliJ instance</li>
        <li>Open File>Settings>Plugins>Marketplace</li>
        <li>Search for Swagger plugin. Keep in mind it may already be installed (check in the Installed section too)</li>
        <li>Install it if it has not been</li>
        <li>Open the yml/json file with the Swagger content</li>
        <li>You will see a small IntelliJ icon around the top right corner</li>
        <li>Press it and you will be able to see the swagger definition</li>
    </ol>
</details>
<details>
    <summary>Use online Swagger reader</summary>
    <ol>
        <li>This is the easier but not so comfortable approach.</li>
        <li>You need to copy the content of the json/yml file representing the Swagger documentation</li>
        <li>Open https://editor.swagger.io/</li>
        <li>Delete everything from the left-side panel</li>
        <li>Paste the copied content</li>
        <li>The swagger will be visualized in the right panel</li>
    </ol>
</details>

#### Generate
The generated OpenApi file can be located under target/openapi.json.
<br>

To generate a new file representing the current changes in your branch just
execute <code>mvnw.cmd clean verify</code> from your Windows terminal.
