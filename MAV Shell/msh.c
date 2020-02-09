// The MIT License (MIT)
// 
// Copyright (c) 2016, 2017 Trevor Bakker 
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

#define _GNU_SOURCE

#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <signal.h>

#define WHITESPACE " \t\n"      // We want to split our command line up into tokens
                                // so we need to define what delimits our tokens.
                                // In this case  white space
                                // will separate the tokens on our command line

#define MAX_COMMAND_SIZE 255    // The maximum command-line size

#define MAX_NUM_ARGUMENTS 10     // Mav shell only supports 10 arguments

#define Max_hist_size 15

int main()
{
  int i = 0;
  pid_t pid = 0;
  //int h = 0;
  char * cmd_str = (char*) malloc( MAX_COMMAND_SIZE );
  static char *history[Max_hist_size];
  int unsigned hist_count = 0;
  //bzero(cmd_str, sizeof( MAX_COMMAND_SIZE ));

  while( 1 )
  {	
  
  	int listpids[14];
  	printf("The PID at the start of the while forever loop is: %d\n",pid);
  	char *new_dir_to = NULL;
    // Print out the msh prompt
    printf ("msh> ");

    // Read the command from the commandline.  The
    // maximum command that will be read is MAX_COMMAND_SIZE
    // This while command will wait here until the user
    // inputs something since fgets returns NULL when there
    // is no input
    while( !fgets (cmd_str, MAX_COMMAND_SIZE, stdin) );

    /* Parse input */
    char *token[MAX_NUM_ARGUMENTS];

    int   token_count = 0;                                 
                                                           
    // Pointer to point to the token
    // parsed by strsep
    char *arg_ptr;                                         
                                                           
    char *working_str  = strdup( cmd_str );    
    
    printf("working string - %s\n", working_str);            

    // we are going to move the working_str pointer so
    // keep track of its original value so we can deallocate
    // the correct amount at the end
    char *working_root = working_str;
    
    

    // Tokenize the input stringswith whitespace used as the delimiter
    while ( ( (arg_ptr = strsep(&working_str, WHITESPACE ) ) != NULL) && 
              (token_count<MAX_NUM_ARGUMENTS))
    {
      token[token_count] = strndup( arg_ptr, MAX_COMMAND_SIZE );
      if( strlen( token[token_count] ) == 0 )
      {
        token[token_count] = NULL;
      }
        token_count++;
    }
    
	printf("Token count - %d\n",token_count);
	
	/*
	char command[9];
	int i=0;
	while(token_count != 0)
	{
		strcpy(command, token[token_count]);
		i++;
		token_count--;
	}
	*/
	
		
	
    // Now print the tokenized input as a debug check
    // \TODO Remove this code and replace with your shell functionality

    int token_index  = 0;
    //char args[9];
    
    for( token_index = 0; token_index < token_count; token_index ++ ) 
    {
      printf("token[%d] = %s\n", token_index, token[token_index] ); 
      //printf("%s\n",token[token_index]);
      //args[token_index] = args[token_index] + token[token_index];
      //strcpy(args,token[token_index]);
    }
    
    //printf("%s", args);
	
		
    free( working_root );
    
	 // Listing PIDs code
	
	if (pid != 0)
		{
			printf("We are in the listpids process.\n");
			i = i + 0;
			if (i < 15)
				{
					listpids[i] = pid;
					i++;
					printf("The i value in PIDs code in if loop is - %d\n",i);
					//printf("Listpids[%d] = %d\n",i,listpids[i]);
					fflush(NULL);
				}
			else
				{
					i = 0;
					printf("The i value in PIDs code in else loop is - %d\n",i);
				}
		}
		
	if ( strlen( cmd_str ) != 0 )
		{	
			if (hist_count < Max_hist_size)
				{
					history[hist_count++] = strdup( cmd_str );
				}
			else
				{
					free(history[0]);
					for (int unsigned h = 1 ; h < Max_hist_size ; h++)
						{
							history[h - 1] = history[h];
						}
					history[Max_hist_size - 1] = strdup( cmd_str );
				}
		}
				
			
				
	/*if ( strlen( cmd_str ) != 0 )
		{
			printf("History function is implemented\n");
			h = h + 0;
			if (h < 15)
				{
					history[h] = working_str;
					h++;
					printf("history[%d] = %c\n",h,working_str[h]);
					fflush(NULL);
				}
			else
				{
					h = 0;
					printf("h is made %d in else condition\n", h);
				}
		}
	*/	
  
	// ------------------------------------------------------------------------------
	
	if (token[0] == NULL)
		{
			printf ("");
			pid = 0;
		}
	else if (!strcmp(token[0], "exit") || !strcmp(token[0], "quit"))
		{	
			return 0;
		}
	
	else if (token_count > 10)
		{
			// printf("Supports only 10 parameters try again.\n");
			printf ("");
			pid = 0;
		}
	
	else if (!strcmp(token[0], "history"))
		{
			if ( hist_count == Max_hist_size )
				{
					for (int h_count = 0 ; h_count < hist_count ; h_count ++)
					printf("%d: %s", h_count, history[h_count]); 
				}
			else
				{
					for (int h_count = 0 ; h_count < hist_count ; h_count ++)
					printf("%d: %s", h_count, history[h_count]); 
				}
			pid = 0;
		}

	
	else if (!strcmp(token[0], "listpids"))
		{	
			if ( i == 14 )
				{
					for (int pid_count = 0 ; pid_count < 15 ; pid_count ++)
					printf("%d: %d\n", pid_count, listpids[pid_count]); 
				}
			else
				{
					for (int pid_count = 0 ; pid_count < i ; pid_count ++)
					printf("%d: %d\n", pid_count, listpids[pid_count]); 
				}
			pid = 0;
		}	
		
	else if (token_count < 10 && strcmp(token[0], "cd"))
		{
			printf("Fork is working\n");
			int status;
    		int exec_error_no;
    		pid = fork();
    		if (pid == 0)
    			{
    				// We are in the child process
					printf("The curent directory in fork process - %s\n", new_dir_to);
					printf("The token[0] which contains the file name - %s\n",token[0]);
					printf("The token[1] which contains the path - %s\n",token[1]);
    				exec_error_no = (execvp(token[0],token));	
    				printf("The exec error number - %d\n", exec_error_no);
    				if  (exec_error_no == -1)
    					{	
    						printf("%s: Command not found.\n",*token);
    						pid = 0;
    						break;
    					}
    				/*else
    					{
 							exit(errno);
 						}*/   	
    				fflush(NULL);
    			}
    			
    		else if (pid > 0)
    			{
    				// We are in the parent process and want this process to wait
    				// So that child process is completed first
    				printf("The PID in the fork process is: %d\n",pid);
					if (WIFEXITED (status))
    					{	
    						waitpid(pid, &status, WUNTRACED);
    						//printf("errno printed.%d\n",status);
    					}
    				fflush(NULL);
    			}		
		}
		
	else if (!strcmp(token[0], "cd"))
		{
			// To check the string compare is working
     		printf("Hello the string compare is working\n");
     		pid = 0;
     		
     		if (token_count == 3)
     			{
     				// To check token count  (Not working)
    			    printf("Hello the token count is working\n");
    			    
    			    char *get_current_dir;
					char *new_dir;
    				//char *new_dir_to;
    				char var[100];
    			
    				
    				get_current_dir = getcwd(var, sizeof (var));
    				printf("The curent working directory in cd loop - %s\n", get_current_dir);
    				new_dir = strcat(get_current_dir,"/");
    				printf("The concatenated working directory in cd loop - %s\n", new_dir);
    				new_dir_to = strcat(new_dir, token[1]);
    				printf("The actual directory now which will be used for executing any command after this - %s\n", new_dir_to);
    				chdir(new_dir_to);
    				fflush(NULL);
    				//continue;
    			}
    		else
    			{
    				printf ("");
    			}
		}
	else 
		{
    		printf ("");
    	}
    	
   
	// ------------------------------------------------------------------------------
	
	
  }
  return 0;
}


  // ---------------------------------------------------
    
    
    
    // --------------------------------------------------
    
    
