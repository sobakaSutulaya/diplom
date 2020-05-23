export interface Event {
    id: string;
    name: string;
    description: string;
    taskIds: Array<string>;
    startDate: Date;
    endDate: Date;
    subject: string;
    courseNumber: number;
    teacherId: string;
    groupNames: Array<string>;

}