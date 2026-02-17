# Pet Shelter Management System

## Project Overview

The Pet Shelter Management System will track various animals throughout their time at the shelter.
Data will be recorded for each animal including but not limited to `id`, `species`, and `age`. 
The system will also track actions of the shelter and staff such as medical examinations, pet progression, and pet care.  

There will be classes for `Animal`, `Staff`, and a `TaskList` that tracks daily jobs.

## Planned Design Patterns

The system will use the Observer pattern to manage the interactions of `Staff` 
and `Animal`s such as care and medical actions. The system will also use the 
State pattern to manage animal status including health and progression states.

## Integration Strategy

The `Staff` instances in the simulation will be able to observe `Animal`s and react to the events / changes they produce.
For example, an `Animal` could get sick, and `Staff` would react to that by administering medicine.

Both `Staff` and `Animal`s will have state that impacts how they behave. For example, `Staff` will perform different
actions and perform different types of tasks depending upon their `role`.

## Requirements Focus

- R1.1: Start with at least 5 animals and track basic attributes (ID, species, age, health status)
- R1.3: Maintain a simple status for each animal (intake, available, pending, adopted)
- R2.1: Define at least two staff roles (e.g., veterinarian, technician, counselor)
- R2.2: Each staff member has a capacity and can be assigned to animals or tasks
- R4.1: Provide basic care actions (feeding, cleaning, exercise, or socialization)
- R4.2: Schedule a simple medical action (e.g., intake exam or vaccination)

## Concerns / Questions

- I am concerned that these might not be all the design patterns that end up in the final build.
- State transitions may be difficult to track and in turn make debugging the system more difficult.
- Debugging will also be difficult because of the indirect communication of the observer pattern. 