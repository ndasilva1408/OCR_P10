export interface Billet {
 id?: number;
 bookerId?: string;
 bookId?: string;
 biblioId?: string;
 provenance?: string;
 bookingDate?: Date;
 endDate?: Date;
 extendDate?: Date;
 isExtend?: boolean;
 isOnWaitList?: boolean;
 isExtendable?: boolean;
}
