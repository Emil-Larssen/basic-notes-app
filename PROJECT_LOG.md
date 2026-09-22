## First Project Overview
Project goal: Build a Spring Boot web application where a user can
create a text note and view all previously created notes.

Functional requirements:
* Show one page with a text area and submit button.
* User enters note text.
* Submitting adds the note to an in-memory collection.
* The page displays all notes currently stored in memory.
* Restarting the application clears the notes.

### Tools
Maven,
Spring Web,
ThymeLeaf,
In-memory/ No database

This is a procedural log that simply records every relevant step/bottleneck/consideration along the way.  
A sort of stream of consciousness if you will.

----------
## 22/09/2026
**First success!**
Figured out how to initialize project with correct dependencies  
And extract to project folder to then open in IDE, that's a start!

**To Git or Not to Git**  
I should probably create a git repository right off the bat...

**How do I even run this thing?**  
Gonna see what the initial setup does on its own.  
And thus I learned to access it on http://localhost:8080  

**Weird thing**  
I had already removed a duplicate folder after extracting the zip from spring initializr  
Apparently there was yet another one on the initial commit. Now corrected for a critical commit.  
Not sure if I missed something, but I might have.

**So now what?**  
There's a placeholder page on localhost as expected, but this is where the confusion arrives.  
Where do I begin this thing???  
I'm guessing a good place to start would be to create the structure for the webpage.

I will begin by making a simple page view with just a title.  
The question is, where???

index.html placed within the main/resources/templates folder.
displays directly on localhost. Therefore a plain html page with a heading,  
a text input field and a button to submit has been created.  
Now the question arises: what are the posted notes displayed in ?
What html item to use???

The decision fell on a simple unordered list with a single placeholder item for now.  
Thats basically the html for this project.  
Next up the big question, What it do?  
Here begins the chain, I need to be able to capture the text in the input field when i press the button,  
the backend then POST it to a note item (which is a list item), following that the page should automatically  
fetch and display all the notes. Thats basically the entire functionality of this project.  
So what does that mean? I suppose it means I gotta make the button link to a method that will post the content.  

But before that, lets do some TDD.  
First test should probably just assert that a 200 OK response happens, but what do I know.  
Also, what test ? How ?

First, figure out what class tests should live in.  
That means we have to know what class we are testing for because naming of the testclass follows that.  
So now we have to consider the architecture already. (sort of)  
Anyway, we are posting and viewing "Notes" so we will probably get a class with that.  
In order to manage those notes we probably need a NoteService (at least that seems like a reasonable choice presently).

But wait!, dependencies???  
I have been led to believe that spring-boot-starter-test should be used here.  
The pom.xml I got form spring initializr has the ones with webmvc-test and thymeleaf-test but not the plain -test.  
So lets add that in (dont ask me if this is necessary, I have no idea).

Anyway, returning to the test.
What we want to test is probably that when a note is added to the noteList (a list residing in Notes.java)  
But hang on, packages??? since this a simple app do we just leave everything in the same package? is that a good idea??  
Ok, no, not a good idea, lets at least make a basic structure which appears to be model, controller and service.

Ive been fumbling a bit around with the test and accidentally made it require a "Notes.java", but that doesnt follow,  
The NoteService.java should handle the lifecycle of a Note.java, which does NOT have any functionality, it is simply string objects.  
Thus the noteShouldBeAddedToNoteList() now creates an instance of NoteService and NOT Note(s).  
Silly me, I stand corrected once again.  

And once again we backtrack a little.
There are now two test, one testing that the noteslist is not empty after a note is added, and a second test that  
the content is indeed the correct one.
Getting these green now means that the core behaviour of the project is now implemented seeing as the plumbing to post  
a note into the noteslist in the backend actually works.  
Next will be a matter of making the textfield from the webpage become the content posted in the noteslist.
