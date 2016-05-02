# Project: PINF2016

How to contribute:
    
  - clone this repository using ssh:
    ```sh
    $ git clone git@github.com:remixgit/PINF2016.git
    ```
  - clone this repository using https:
    ```sh
    $ git clone https://github.com/remixgit/PINF2016.git
    ```
  - if you already cloned it go to master:
    ```sh
    $ git checkout master
    ```
    and pull the latest version:
    ```sh
    $ git pull
    ```   
- [IMPORTANT] everything you do, do on a separate branch, not on master! Create new branch git checkout -b NAME_OF_THE_BRANCH
    where NAME_OF_THE_BRANCH should look like "your_initials" + "/name-of-the-feature". Example: git checkout -b mv/components
- Do something on your branch. (Something useful would be nice.)
- Commit and push your work.
    ```sh
    $ git add .
    $ git commit -am "COMMENT"
    $ git push origin NAME_OF_THE_BRANCH
    ```  
- Submit a pull request for review. https://help.github.com/articles/creating-a-pull-request/
