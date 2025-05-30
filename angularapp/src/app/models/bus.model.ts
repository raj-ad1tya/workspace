import { User } from "./user.model";

export interface Bus {
    busId?: number;
    busName: string;
    source: string;
    destination: string;
    departureTime: string;
    arrivalTime: string;
    totalSeats: number;
    availableSeats: number;
    busType: string;
    fare: number;
    photo?: string;
    user?: User;
    userId: number;
}
