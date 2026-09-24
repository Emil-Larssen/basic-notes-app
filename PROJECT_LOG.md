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


## 24/09/2026
After a bit of deliberation, what comes to mind is that I will now create the test responsible for ensuring that a  
post request successfully puts the contents of the textfield from the webpage into a note in the noteslist.
Meaning we will write the test for the POST endpoint.
Supposedly that means a NoteControllerTest, which incorporates the @WebMvcTest annotation.  

What I dont know now is:  
How do I even test that ? I know what im testing, but not HOW to test it.  
First im guessing I need to know how submitting a note from the page initiates a POST request.  
I have no idea how to do this, which has guided me towards MockMvc.  
Ok, so using MockMvc I can .perform(post("/notes")) and then I can extend that with .andExpect(status().isOk()) to get  
back a 200 OK statuscode.
So that is the first test (which will obviously fail because we havent made a post endpoint yet,  
red green refactor loop and all that).  
And the test as expected comes back with a 404 response since I have not actually made a post endpoint yet,  
Not even the NoteController, so thats the first step now.  

Attempted to build the method submitNote as a PostMapping. But method was a void, and returned nothing.  
So instead the method return should be a responseEntity simply echoing back a 200 status code. to satisfy what the test expects.  
I expect the test to now pass because all its expecting is that 200 OK code despite not actually posting anything yet,  
so after running the test and passing it, the test will be expanded to test that we actually post something.

Now I want to test that a text actually gets posted to the backend and is equal to the string we choose to post.  
Which is yet again another "I dont know that", so here we go again.  
After some messing around Im now getting springboot to actually create the noteservice bean, had a bit of a hiccup with that.  
Which now brings me to the error NoSuchElement, indicating that no, nothing was posted, because the noteslist in the NoteService is empty.

Learning moment: add in requestparameter to the submitNote method in the controller for the PostMapping.  
Also, lets not forget that NoteController needs to know about NoteService, so give it a field and give NoteController  
a constructor which takes a noteService instance (Yes I forgot that).

**Crazy**  
That's a successfull test meaning the post ACTUALLY makes the posted string appear in a note within the noteslist.  

Ok, I got a bit lost in the GET thing and forgot to write much.
But essentially, as I constructed a Get test, I somehow got back the index.html file and its contents. Which is obviously  
NOT what I was trying to do.
Some black magic happened with mockmvc resultmatcher and stuff, which finally got me to writing the @GetMapping in the controller.  
Also encountered the springframework.ui.model, which is NOT related to the model package in my MVC structure.
Got to also define in the html how thymeleaf then generates a list item.

Next problem now. Am I missing a redirect? the test pass, but the application does not work.  
Therefore my tests must be incomplete.  

The test for posting had to be altered away from returning a 200 statuscode and instead return a redirect.
Tests then passed but....
Stuff missing in the html...  
Note to self, form needs to include an action AND method.  

## Done??? 
after correcting the form in the html the app actually works for the intended scope.
Which is really just writing something in the textfield, pressing the button, and the text appears as a list item.  
That was a mouthful but yay, thing does stuff...

Untill next time!
