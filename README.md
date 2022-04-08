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
