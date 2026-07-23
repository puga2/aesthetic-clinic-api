# Clinic

# Aesthetic Clinic SaaS — Business Case Scenario

## 1. Background

Aesthetic and skincare clinics — especially small-to-mid-sized ones across Cambodia and similar markets — typically run their day-to-day operations on a patchwork of disconnected tools: a paper appointment book or a shared Excel sheet, a Telegram/WhatsApp group for staff coordination, a separate notebook for tracking product stock, and a cash drawer or a basic POS app for billing. Client photos (before/after treatment) are often stored loosely on staff phones or in a shared Google Drive folder with no link back to the client's actual treatment record.

As the clinic grows — adding more therapists, more branches, or a wider range of services (facial, laser, hair/scalp, PRP, microneedling) — this patchwork breaks down.

## 2. The Problem

**2.1 Fragmented client records**
A client's phone number might be in the booking sheet, their allergy history in a paper form, and their before/after photos on a staff member's personal phone. No single place shows the full picture, which creates real risk when doing sensitive treatments (e.g. missing a documented allergy).

**2.2 No visibility into treatment progress**
Many aesthetic treatments (laser hair removal, PRP, microneedling) are sold as multi-session packages. Without a system tracking session number, notes, and images per session, it's hard for staff to know how many sessions a client has completed, what was used, or how their skin/hair has progressed — and hard for the clinic to prove the value of the package to the client.

**2.3 Manual, error-prone inventory**
Product stock (serums, numbing cream, disposables) is consumed during treatments but rarely deducted in real time. Clinics frequently discover they're out of a product mid-treatment, or over-order because nobody has an accurate live count.

**2.4 Billing and revenue leakage**
Cash-based or informal billing makes it difficult to reconcile daily revenue, apply consistent discounts/memberships, or reconstruct what was actually paid for a given visit. Refunds and partial payments are hard to track.

**2.5 No membership or retention tooling**
Clinics want to sell membership packages (e.g. prepaid facial bundles, VIP plans) to improve retention and cash flow, but without a system to track plan balances, expiry, and usage, this becomes a manual, error-prone process run on trust.

**2.6 No structured reporting**
Owners/managers can't easily answer basic questions — "What was our revenue this month?", "Which service is most profitable?", "Which products are running low?" — without manually compiling numbers from multiple sources.

**2.7 No audit trail**
When something goes wrong (a client complaint, a missing product, a disputed charge), there's no reliable record of who did what and when.

## 3. Who Is Affected

| Role | Pain Point |
| --- | --- |
| **Receptionist** | Juggles phone bookings, paper diary, and messaging apps to manage the daily schedule; struggles to avoid double-booking staff. |
| **Therapist** | Has no quick way to pull up a client's allergy history, past sessions, or before/after photos before starting treatment. |
| **Manager/Owner** | Cannot get a real-time view of revenue, inventory levels, or staff performance; relies on manual, delayed reporting. |
| **Client** | Has to re-explain medical history at every visit; has no visibility into their own package/session progress. |

## 4. The Opportunity

A single, purpose-built platform that unifies client records, scheduling, treatment history, inventory, billing, and memberships would let a clinic:

- Reduce no-shows and double-bookings through a real scheduling system with status tracking (booked → confirmed → completed/no-show)
- Improve treatment safety and consistency by surfacing medical history and allergies at the point of care
- Prove treatment value to clients with a visual before/after record tied to each session
- Cut inventory loss by tying stock movements directly to treatments and purchase orders, so quantities are always auditable and rebuildable
- Reduce billing errors and revenue leakage with structured invoices, itemized line items, and multiple payment method support (Cash, ABA, KHQR, Stripe)
- Increase retention and predictable cash flow through trackable membership plans
- Give management real-time, structured reports instead of end-of-month manual reconciliation
- Support growth into multiple branches without re-architecting the system, since branch-level data separation is built in from the start
- Lay the foundation for future AI-assisted skin/hair analysis, since client images and structured treatment data are already centralized

## 5. Proposed Solution (Summary)

A modular Aesthetic Clinic SaaS platform, built as:

- **Frontend:** Next.js + TypeScript + TailwindCSS + ShadCN UI dashboard for staff, with calendar-based scheduling, client profiles, and before/after comparison views
- **Backend:** Spring Boot 3 (Java 21) REST API with PostgreSQL, organized around clear domain modules — Authentication, Client Management, Appointment, Treatment, Inventory, Billing, Membership, Reports, Media, Notification, and (future) AI Analysis
- **Media storage:** Cloudinary for before/after images (URLs only stored in the database, never binary image data)
- **Multi-branch support:** every core business table is scoped by `branch_id`, so the same platform can serve a single clinic today and a multi-branch chain tomorrow without redesign
- **Audit logging:** every sensitive action (login, client update, product deletion, appointment creation) is recorded for accountability

The database is deliberately split into ~50 focused tables rather than a few large ones — for example, separating `appointments` (the booking) from `treatment_sessions` (the actual multi-session package delivery), and never mutating inventory quantity directly but instead recording it through an append-only `stock_movements` ledger — so that the system stays auditable and reconstructable as the business scales.

## 6. Expected Outcomes

- Faster, error-free front-desk booking and check-in
- Safer treatments through visible medical/allergy history
- Provable treatment progress for clients (before/after history)
- Real-time, low-loss inventory management
- Clean, reconcilable daily/monthly revenue reporting
- Higher client retention via trackable membership plans
- A platform architecture ready to expand into multi-branch operations, a client-facing mobile app, and AI-powered skin/hair analysis

## 7. Success Metrics (suggested)

- Reduction in no-show rate after introducing appointment status tracking and reminders
- Reduction in inventory discrepancies (recorded vs. physical stock count)
- Time saved per day on manual billing/reconciliation
- % of clients enrolled in a membership plan
- Staff time to retrieve a client's full history (before vs. after the system)
