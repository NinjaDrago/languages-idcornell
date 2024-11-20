% Import the required library for constraints
:- use_module(library(clpfd)).

% Define the Sudoku solver
sudoku(Grid) :-
    length(Grid, 3),                      % Ensure grid has 3 rows
    maplist(same_length(Grid), Grid),     % Ensure each row has 3 elements
    append(Grid, Cells),                  % Flatten grid to a list of cells
    Cells ins 0..9,                        % Allow 0 as a valid "unfilled" cell
    maplist(all_distinct_no_zeros, Grid), % Each row must have unique numbers
    transpose(Grid, Columns),             % Get the columns of the grid
    maplist(all_distinct_no_zeros, Columns), % Each column must have unique numbers
    subgrids(Grid, SubGrids),             % Extract 3x3 subgrids
    maplist(all_distinct_no_zeros, SubGrids). % Each subgrid must have unique numbers

% Helper predicate to ensure all elements in a list are distinct, excluding 0
all_distinct_no_zeros(Row) :-
    exclude(=(0), Row, FilteredRow),      % Remove zeros
    all_distinct(FilteredRow).             % Apply all_distinct to the remaining elements

% Extract 3x3 subgrids from the grid
subgrids([], []).
subgrids([A, B, C | Rows], [Grid1, Grid2, Grid3 | SubGrids]) :-
    blocks(A, B, C, Grid1, Grid2, Grid3), % Extract blocks for first 3 rows
    subgrids(Rows, SubGrids).             % Recur for the rest of the rows

% Extract blocks of three elements from three rows
blocks([], [], [], [], [], []).
blocks([A1, A2, A3 | RestA], [B1, B2, B3 | RestB], [C1, C2, C3 | RestC],
       [A1, B1, C1], [A2, B2, C2], [A3, B3, C3]) :-
    blocks(RestA, RestB, RestC, _, _, _).  % Ignore unused rest variables

% Solve the Sudoku by labeling the cells
solve(Grid) :-
    sudoku(Grid),                  % Validate Sudoku constraints
    writeln('Sudoku constraints satisfied. Solving...'), % Debug: Confirm constraints
    maplist(label, Grid),          % Assign numbers to variables
    writeln('Solved Grid:'),       % Debug: Output solved grid
    maplist(writeln, Grid).

% Test predicate with a simplified 3x3 Sudoku puzzle
test_sudoku :-
    Grid = [
        [5, 3, 0],
        [6, 0, 0],
        [0, 9, 8]
    ],
    writeln('Testing 3x3 Sudoku Solver...'), % Debug: Indicate test is running
    solve(Grid).
